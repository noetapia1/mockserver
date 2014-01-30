package org.mockserver.client.serialization.model;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.mockserver.model.Parameter;
import org.mockserver.model.ParameterBody;

import java.util.List;

/**
 * @author jamesdbloom
 */
public class ParameterBodyDTO extends BodyDTO {

    private List<ParameterDTO> parameters;

    public ParameterBodyDTO(ParameterBody parameterBody) {
        super(parameterBody.getType());
        parameters = Lists.transform(parameterBody.getParameters(), new Function<Parameter, ParameterDTO>() {
            public ParameterDTO apply(Parameter parameter) {
                return new ParameterDTO(parameter);
            }
        });
    }

    protected ParameterBodyDTO() {
    }

    public List<ParameterDTO> getParameters() {
        return parameters;
    }

    public ParameterBody buildObject() {
        return new ParameterBody(Lists.transform(parameters, new Function<ParameterDTO, Parameter>() {
            public Parameter apply(ParameterDTO parameterDTO) {
                return parameterDTO.buildObject();
            }
        }));
    }
}
