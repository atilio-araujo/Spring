package br.com.alaraujo.musicdashboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CustomErrorController implements ErrorController{

	private static final String errorPath = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

	@RequestMapping(value = errorPath)
	public ModelAndView error(HttpServletRequest request, RedirectAttributes redirectAttributes){
		ModelAndView view = new ModelAndView();
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		
		String path = (String) this.errorAttributes.getErrorAttributes(requestAttributes, false).get("path");
		path = path.replaceAll(request.getServletContext().getContextPath(), "");

		redirectAttributes.addFlashAttribute("errorMessage", "We couldn't process your request. Please try again!");
		view.setViewName("redirect:" + path);
		
		return view;
	}

	@Override
	public String getErrorPath() {
		return errorPath;
	}
}
