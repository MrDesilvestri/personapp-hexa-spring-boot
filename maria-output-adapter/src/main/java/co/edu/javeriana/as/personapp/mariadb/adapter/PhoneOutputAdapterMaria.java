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
    private TelefonoRepositoryMaria repo;
    @Autowired
    private TelefonoMapperMaria mapper;

    @Override
    public Phone save(Phone phone) {
        TelefonoEntity entity = mapper.fromDomainToAdapter(phone);
        entity = repo.save(entity);
        return mapper.fromAdapterToDomain(entity);
    }

    @Override
    public Boolean delete(Integer num) {
        repo.deleteById(num);
        return !repo.existsById(num);
    }

    @Override
    public Phone findById(Integer num) {
        return repo.findById(num)
                   .map(mapper::fromAdapterToDomain)
                   .orElse(null);
    }
}
