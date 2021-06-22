package com.eventosapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.Evento;
import com.eventosapp.repository.ConvidadoRepository;
import com.eventosapp.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository er;

	@Autowired
	private ConvidadoRepository cr;
	
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public ModelAndView form(Evento evento) {
		ModelAndView modelAndView = new ModelAndView("evento/formEvento");
		modelAndView.addObject("evento", evento);
		return modelAndView;
	}

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String salvar(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos estão vazios ou com erros!");
			return "redirect:/cadastrarEvento";
		}
		er.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento adicionado com sucesso!");
		return "redirect:/cadastrarEvento";
	}

	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}

	
	
	  @RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	  public ModelAndView detalhesEvento(@PathVariable("codigo") Long codigo) {
	    Evento evento = er.findByCodigo(codigo);
	    ModelAndView mv = new ModelAndView("evento/detalhesEvento");
	    mv.addObject("evento", evento);
	  
	    Iterable<Convidado> convidados = cr.findByEvento(evento);
	    mv.addObject("convidados", convidados);
	    return mv;
	  }
	
		
	@RequestMapping("/deletarEvento")
	public String deletarEvento(Long codigo){
		Evento evento = er.findByCodigo(codigo);
		er.delete(evento);
		return "redirect:/eventos";
	}
	

	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado,  BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		Evento evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/{codigo}";
	}

	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(String rg) {
		Convidado convidado = cr.findByRg(rg);
		cr.delete(convidado);

		Evento evento = convidado.getEvento();
		long codigoLong = evento.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
	
	/* Implementação da Edição de Eventos */
	
	  @RequestMapping(value="/editarEvento/{codigo}", method=RequestMethod.GET)
	  public ModelAndView edicao(@PathVariable("codigo") Long codigo) {
	    Evento evento = er.findByCodigo(codigo);
	    ModelAndView mv = new ModelAndView("evento/edicao");
	    mv.addObject("evento", evento);
	    er.save(evento);
	    return mv;
	  }
	
	

}
