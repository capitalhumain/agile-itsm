/*
 *     Copyright 2014 Modo Ágil
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package br.com.agileitsm.model.support;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

/**
 * Abstract base entity for model entity<br />
 *
 * All model entities from model must extend this class or a sub-class
 *
 * @author Bruno César - <a href="mailto:bruno@modoagil.com.br">bruno@modoagil.com.br</a>
 * @since 10/03/2015
 */
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 278818851135160366L;

    private Integer id; // TODO change to Long

    private Long version;

    private Date changeDate;

    private Boolean removed = false;

    public Boolean isRemoved() {
        return removed;
    }

    public boolean isNew() {
        return getId() == null;
    }

    @Override
    public String toString() {
        return String.format("%s id: %s", this.getClass().getSimpleName(), getId());
    }

}
