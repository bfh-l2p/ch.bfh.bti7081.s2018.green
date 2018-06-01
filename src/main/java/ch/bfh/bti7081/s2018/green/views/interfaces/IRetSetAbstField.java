package ch.bfh.bti7081.s2018.green.views.interfaces;


import com.vaadin.ui.AbstractField;

public interface IRetSetAbstField {

    default <T> AbstractField<T> retModVal(AbstractField<T> cmp, T val) {
        cmp.setValue(val);
        return cmp;
    }
}
