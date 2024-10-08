package co.chepito.seguros.seguroschepitoapiprocesador.broker;

import co.chepito.seguros.seguroschepitoapiprocesador.data.mapper.concrete.CotizacionMapperEntity;
import co.chepito.seguros.seguroschepitoapiprocesador.data.mapper.concrete.IUsuarioMapperEntity;
import co.chepito.seguros.seguroschepitoapiprocesador.domain.productos.CotizacionDomain;
import co.chepito.seguros.seguroschepitoapiprocesador.domain.usuario.UsuarioDomain;
import co.chepito.seguros.seguroschepitoapiprocesador.repository.productos.CotizacionRepository;
import co.chepito.seguros.seguroschepitoapiprocesador.repository.usuario.IUsuarioRepositorio;
import co.chepito.seguros.seguroschepitoapiprocesador.utils.gson.MapperJsonObjeto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReceiverMessageUsuario {

    private final IUsuarioRepositorio usuarioRepositorio;
    private final MapperJsonObjeto mapperJsonObjeto;
    private final IUsuarioMapperEntity usuarioMapperEntity;



    public ReceiverMessageUsuario(IUsuarioRepositorio usuarioRepositorio, MapperJsonObjeto mapperJsonObjeto, IUsuarioMapperEntity usuarioMapperEntity) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.mapperJsonObjeto = mapperJsonObjeto;
        this.usuarioMapperEntity = usuarioMapperEntity;
    }

    @RabbitListener(queues = "usuario")
    public void receiveMessageCotizacion(String json)
    {
        Optional<MessageEvent> messageEvent = mapperJsonObjeto.ejecutar(json, MessageEvent.class);

        messageEvent.ifPresent(msg -> {
            Optional<UsuarioDomain> data = mapperJsonObjeto.ejecutarDesdeJson(msg.getMessage(), UsuarioDomain.class);
            data.ifPresent(usuario -> {
                switch (msg.getEvent()){
                    case "creacion":
                        usuarioRepositorio.save(usuarioMapperEntity.toEntity(usuario));
                        break;
                    case "actualizacion":
                        System.out.println("Usuario: " + usuario.getId());
                        usuarioRepositorio.save(usuarioMapperEntity.toEntity(usuario));
                        break;
                    case "eliminacion":
                        usuarioRepositorio.deleteById(usuario.getId());
                        break;
                    case "edicionparcial":
                        usuarioRepositorio.save(usuarioMapperEntity.toEntity(usuario));
                        break;
                }
            });
        });
    }
}
