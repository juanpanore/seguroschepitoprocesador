package co.chepito.seguros.seguroschepitoapiprocesador.repository.productos;

import co.chepito.seguros.seguroschepitoapiprocesador.data.entity.productos.ClausulaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClausulaRepository  extends JpaRepository<ClausulaEntity, UUID> {
}