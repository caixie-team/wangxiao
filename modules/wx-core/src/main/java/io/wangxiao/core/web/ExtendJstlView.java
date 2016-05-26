package io.wangxiao.core.web;

import org.springframework.web.servlet.view.JstlView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ExtendJstlView extends JstlView {
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.render(model, request, response);
	}
}
