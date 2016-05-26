package com.atdld.os.core.util.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.JstlView;

public class ExtendJstlView extends JstlView {
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.render(model, request, response);
	}
}
