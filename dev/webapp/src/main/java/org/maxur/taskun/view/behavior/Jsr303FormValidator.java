package org.maxur.taskun.view.behavior;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.spring.injection.annot.SpringBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
public class Jsr303FormValidator implements IFormValidator {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -8203929218331278043L;

    @SpringBean
    private transient Validator validator;

    private final FormComponent<?>[] components;

    public Jsr303FormValidator(FormComponent<?>... components) {
        this.components = components;
        injectDependencies();
    }

    private void injectDependencies() {
        InjectorHolder.getInjector().inject(this);
    }

    @Override
    public FormComponent<?>[] getDependentFormComponents() {
        return this.components.clone();
    }

    @Override
    public void validate(final Form<?> form) {
        final Set<ConstraintViolation<Object>> validate = this.validator.validate(form.getModelObject());
        ConstraintViolation[] res = validate.toArray(new ConstraintViolation[validate.size()]);
        for (ConstraintViolation vio : res) {
            form.error(vio.getMessage());
        }
    }

    public static <T> void addToAllComponent(final Form form, final Class<T> clazz) {

        final Set<FormComponent> visited = new HashSet<FormComponent>();

        form.visitChildren(FormComponent.class, new ComponentVisitor() {

            @Override
            protected boolean isAccepted(FormComponent fc) {
                return !(fc instanceof Button);
            }

            @Override
            protected Object doAdd(FormComponent fc) {
                visited.add(fc);
                fc.setRequired(false);
                fc.add(new Jsr303PropertyValidator<T, Object>(clazz, fc.getId()));
                return CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
            }
        });
        final FormComponent[] formComponents = new FormComponent[visited.size()];
        form.add(new Jsr303FormValidator(visited.toArray((formComponents))));
    }
}