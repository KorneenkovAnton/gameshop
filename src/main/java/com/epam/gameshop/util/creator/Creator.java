package com.epam.gameshop.util.creator;

import javax.servlet.http.HttpServletRequest;

public interface Creator<T> {
    T create(HttpServletRequest request);
}
