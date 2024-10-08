package co.chepito.seguros.seguroschepitoapiprocesador.broker;

import co.chepito.seguros.seguroschepitoapiprocesador.data.entity.productos.CotizacionEntity;
import co.chepito.seguros.seguroschepitoapiprocesador.data.entity.usuario.UsuarioEntity;
import co.chepito.seguros.seguroschepitoapiprocesador.data.mapper.concrete.CotizacionMapperEntity;
import co.chepito.seguros.seguroschepitoapiprocesador.domain.productos.CotizacionDomain;
import co.chepito.seguros.seguroschepitoapiprocesador.domain.usuario.UsuarioDomain;
import co.chepito.seguros.seguroschepitoapiprocesador.repository.productos.CotizacionRepository;
import co.chepito.seguros.seguroschepitoapiprocesador.utils.gson.MapperJsonObjeto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ReceiverMessage {

//    private final CotizacionRepository cotizacionRepository;
//    private final MapperJsonObjeto mapperJsonObjeto;
//    private final CotizacionMapperEntity cotizacionMapperEntity;
//
//
//
//    public ReceiverMessage(CotizacionRepository cotizacionRepository, MapperJsonObjeto mapperJsonObjeto, CotizacionMapperEntity cotizacionMapperEntity) {
//        this.cotizacionRepository = cotizacionRepository;
//        this.mapperJsonObjeto = mapperJsonObjeto;
//        this.cotizacionMapperEntity = cotizacionMapperEntity;
//    }
//
//    @RabbitListener(queues = "cotizacion")
//    public void receiveMessageCotizacion(String json)
//    {
//        Optional<MessageEvent> messageEvent = mapperJsonObjeto.ejecutar(json, MessageEvent.class);
//
//        messageEvent.ifPresent(msg -> {
//            Optional<CotizacionDomain> data = mapperJsonObjeto.ejecutarDesdeJson(msg.getMessage(), CotizacionDomain.class);
//            data.ifPresent(cotizacion -> {
//                switch (msg.getEvent()){
//                    case "creacion":
//                    case "actualizacion":
//                        System.out.println("Usuario: " + cotizacion.getUsuario().getId());
//                        cotizacionRepository.save(cotizacionMapperEntity.toEntity(cotizacion));
//                        break;
//                    case "eliminacion":
//                        cotizacionRepository.deleteById(cotizacion.getId());
//                        break;
//                }
//            });
//        });
//    }
}
