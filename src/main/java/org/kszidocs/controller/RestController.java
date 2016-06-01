package org.kszidocs.controller;

import org.kszidocs.web.dto.DocumentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestController {
    @RequestMapping(value = "/documents/add", method = RequestMethod.POST)
    public String addDocumentToGroup(Model model) {
        return "document";
    }
}
