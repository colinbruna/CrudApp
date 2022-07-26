package br.com.unicred.crudapp.infraestructure.adapter.empresa.converter;

import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import br.com.unicred.crudapp.infraestructure.entity.empresa.EmpresaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmpresaEntityConverterImpl implements EmpresaEntityConverter {

    private final ModelMapper mapper;

    @Autowired
    public EmpresaEntityConverterImpl(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EmpresaEntity converterParaEntity(final Empresa empresa) {
        return empresa == null ? null : mapper.map(empresa, EmpresaEntity.class);
    }

    public Empresa converterParaEmpresa(final EmpresaEntity empresaEntity) {
        return empresaEntity == null ? null : mapper.map(empresaEntity, Empresa.class);
    }

    public List<Empresa> converterParaListaEmpresas(final List<EmpresaEntity> empresasEntity) {
        List<Empresa> empresas = new ArrayList<>();

        for (EmpresaEntity empresaEntity : empresasEntity) {
            empresas.add(converterParaEmpresa(empresaEntity));
        }

        return empresas;
    }
}
