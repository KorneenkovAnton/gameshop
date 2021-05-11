package com.epam.gameshop.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class CurrencyParserTag extends SimpleTagSupport {
    public static int exchangeRate = 66;
    private final StringWriter sw = new StringWriter();
    private String lan;

    public void setLan(Object lan) {
        this.lan = (String) lan;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if (lan != null) {
            switch (lan) {
                case "en_US": {
                    getJspBody().invoke(sw);
                    out.print(sw.toString() + " USD");
                    break;
                }
                case "ru_RU": {
                    getJspBody().invoke(sw);
                    out.print(Integer.parseInt(sw.toString()) * exchangeRate + " RUB");
                }
            }
        } else {
            getJspBody().invoke(sw);
            out.print(sw.toString() + " USD");
        }
    }
}
