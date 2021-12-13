package com.t.t.k.ims.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Enable configuration for auditing data
 *
 * @author ttkien
 */

@Configuration
@EnableMongoAuditing
public class MongoDBConfig {

    /**
     * This bean enables adding auditing information to the model before saving to database
     * Auditing information include createdby, createdAt, updatedby, updatedat which is
     * defined in AbstractModel class.
     *
     * @return
     */
    @Bean
    public AuditorAware<BigInteger> myAuditorProvider() {
        return new MongoDByAuditorAware();
    }

    class MongoDByAuditorAware implements AuditorAware<BigInteger> {

        public Optional<BigInteger> getCurrentAuditor() {
            return Optional.ofNullable(null);
        }
    }

    /**
     * Bean for Validating Mongo Event Listener and passes Local Validator Factory Bean to the constructor
     *
     * @return instance of Validating Mongo Event Listener
     */
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    /**
     * Bean for Local Validator Factory Bean
     *
     * @return instance of Local Validator Factory Bean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
