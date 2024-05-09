package co.edu.javeriana.as.personapp.mariadb.adapter;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mariadb.entity.TelefonoEntity;
import co.edu.javeriana.as.personapp.mariadb.mapper.TelefonoMapperMaria;
import co.edu.javeriana.as.personapp.mariadb.repository.TelefonoRepositoryMaria;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter("phoneOutputAdapterMaria")
@Transactional
public class PhoneOutputAdapterMaria implements PhoneOutputPort{
    @Autowired
    private TelefonoRepositoryMaria phoneRepositoryMaria;

    @Autowired
    private TelefonoMapperMaria telefonoMapperMaria;

    @Override
    public Phone save(Phone phone) {
        log.debug("Into save on Adapter MariaDB");
        TelefonoEntity persistedPhone = phoneRepositoryMaria.save(telefonoMapperMaria.fromDomainToAdapter(phone));
        return telefonoMapperMaria.fromAdapterToDomain(persistedPhone);
    }

    @Override
    public Boolean delete(Integer numero) {
        log.debug("Into delete on Adapter MariaDB");
        phoneRepositoryMaria.deleteById(numero.toString());
        return phoneRepositoryMaria.findById(numero.toString()).isEmpty();
    }

    @Override
    public Phone findById(Integer numero) {
        log.debug("Into findById on Adapter MariaDB");
        if(phoneRepositoryMaria.findById(numero.toString()).isEmpty()){
            return null;
        }
        return telefonoMapperMaria.fromAdapterToDomain(phoneRepositoryMaria.findById(numero.toString()).get());
    }
}
