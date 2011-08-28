package org.maxur.taskun.it;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.Id;
import javax.validation.Validator;

import static org.junit.Assert.assertNotNull;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/21/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/applicationContext-test.xml"})
public class ValidatorIT {

    @Autowired
    private Validator validator;

    @Before
    public void setUp() throws Exception {
       // validator = new LocalValidatorFactoryBean();
    }

    @Test
    public void shouldBeNotNull() throws Exception {
        assertNotNull("Validator should be not null", validator);
    }

    @Test
    public void shouldBeReturnTrueOnEntityWithoutAnnotation() throws Exception {
        final Entity entity = new Entity() {
            @Override
            @Id
            public String getIdentifier() {
                return null;
            }

            @Override
            public boolean isNew() {
                return false;
            }
        };
        validator.validate(entity);
    }

}
