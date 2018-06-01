package ch.bfh.bti7081.s2018.green.layouts;

import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Layout;

public class BaseLayoutFabric extends CustomLayout {

    public enum SECTIONS {
        headSection, mainSection, navSection, appSection, footerSection
    }
    public BaseLayoutFabric () {
        this.setTemplateName("baselayout");
    }

    public void setContainer(Layout secContainer, SECTIONS enumSection) {
        this.addComponent(secContainer, enumSection.toString());

    }

}
