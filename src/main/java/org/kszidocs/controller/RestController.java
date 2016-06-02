package org.kszidocs.controller;

import org.kszidocs.service.DocumentService;
import org.kszidocs.service.DocumentsGroupService;
import org.kszidocs.web.dto.DocumentDTO;
import org.kszidocs.web.dto.DocumentsGroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RestController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentsGroupService documentsGroupService;

    @RequestMapping(value = "/documents/add", method = RequestMethod.POST)
    public String addDocumentToGroup(@ModelAttribute("document") DocumentDTO dto,
                                     @RequestParam(value = "file", required = false) MultipartFile file,
                                     RedirectAttributes redirectAttributes) {
        dto.setFile(file);
        documentService.saveDocument(dto);
        return "redirect:/documents"+""; //here must be uuid of group
    }

    @RequestMapping(value = "/groups/add", method = RequestMethod.POST)
    public String addGroup(@ModelAttribute("group") DocumentsGroupDTO dto,
                           @RequestParam(value = "file", required = false) MultipartFile file) {
        documentsGroupService.saveGroup(dto);
        return "redirect:/documents";
    }
}
