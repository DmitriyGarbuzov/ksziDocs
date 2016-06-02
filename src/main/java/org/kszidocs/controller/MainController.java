package org.kszidocs.controller;

import org.kszidocs.service.DocumentService;
import org.kszidocs.service.DocumentsGroupService;
import org.kszidocs.web.dto.DocumentDTO;
import org.kszidocs.web.dto.DocumentsGroupDTO;
import org.kszidocs.web.dto.SearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory
            .getLogger(MainController.class);

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentsGroupService documentsGroupService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "redirect:/documents";
    }

    @RequestMapping(value = "/documents", method = RequestMethod.GET)
    public String documents(Model model) {
        model.addAttribute("groups", documentsGroupService.getAllGroups());
        return "documents";
    }

    @RequestMapping(value = "/documents/{group_uuid}")
    public String getDocumentsByGroup(@PathVariable("group_uuid") UUID groupUuid, Model model) {
        model.addAttribute("groups", documentsGroupService.getAllGroups());
        model.addAttribute("group", documentsGroupService.getGroup(groupUuid));
        model.addAttribute("documents", documentService.getAllDocumentsByGroupUuid(groupUuid));
        return "documents";
    }

    @RequestMapping(value = "/documents/add")
    public String addDocumentToGroup(Model model) {
        model.addAttribute("groups", documentsGroupService.getAllGroups());
        model.addAttribute("document", new DocumentDTO());
        return "document";
    }

    @RequestMapping(value = "/groups/add", method = RequestMethod.GET)
    public String addGroup(Model model) {
        model.addAttribute("group", new DocumentsGroupDTO());
        return "group";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchDocument(Model model) {
        if (((BindingAwareModelMap) model).get("searchDto") == null) {
            model.addAttribute("searchDto", new SearchDTO());
        }
        return "search";
    }

    @RequestMapping(value = "/group/edit/{uuid}", method = RequestMethod.GET)
    public String editGroup(@PathVariable("uuid") UUID uuid, Model model) {
        DocumentsGroupDTO dto = documentsGroupService.getGroup(uuid);
        model.addAttribute("group", dto);
        return "group";
    }

    @RequestMapping(value = "/document/edit/{uuid}", method = RequestMethod.GET)
    public String editDocument(@PathVariable("uuid") UUID uuid, Model model) {
        DocumentDTO dto = documentService.getDocument(uuid);
        model.addAttribute("document", dto);
        return "document";
    }

}
