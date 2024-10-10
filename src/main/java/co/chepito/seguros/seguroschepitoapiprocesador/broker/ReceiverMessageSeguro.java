package co.chepito.seguros.seguroschepitoapiprocesador.broker;

import co.chepito.seguros.seguroschepitoapiprocesador.data.entity.productos.SeguroEntity;
import co.chepito.seguros.seguroschepitoapiprocesador.data.mapper.concrete.SeguroMapperEntity;
import co.chepito.seguros.seguroschepitoapiprocesador.domain.productos.SeguroDomain;
import co.chepito.seguros.seguroschepitoapiprocesador.repository.productos.SeguroRepository;
import co.chepito.seguros.seguroschepitoapiprocesador.utils.gson.MapperJsonObjeto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReceiverMessageSeguro {

    private final SeguroRepository seguroRepository;
    private final MapperJsonObjeto mapperJsonObjeto;
    private final SeguroMapperEntity seguroMapperEntity;

    public ReceiverMessageSeguro(SeguroRepository seguroRepository, MapperJsonObjeto mapperJsonObjeto, SeguroMapperEntity seguroMapperEntity) {
        this.seguroRepository = seguroRepository;
        this.mapperJsonObjeto = mapperJsonObjeto;
        this.seguroMapperEntity = seguroMapperEntity;
    }

    @RabbitListener(queues = "seguro")
    public void receiveMessageSeguro(String json) {
        System.out.println("JSON recibido: " + json);
        Optional<MessageEvent> messageEvent = mapperJsonObjeto.ejecutar(json, MessageEvent.class);

        messageEvent.ifPresent(msg -> {
            Optional<SeguroDomain> data = mapperJsonObjeto.ejecutarDesdeJson(msg.getMessage(), SeguroDomain.class);
            data.ifPresent(seguro -> {
                switch (msg.getEvent()) {
                    case "creacion":
                    case "actualizacion":
                        System.out.println("Seguro ID: " + seguro.getSeguroId());
                        seguroRepository.save(seguroMapperEntity.toEntity(seguro));
                        break;
                    case "eliminacion":
                        seguroRepository.deleteById(seguro.getSeguroId());
                        break;
                }
            });
        });
    }
}
