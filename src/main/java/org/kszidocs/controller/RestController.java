package org.kszidocs.controller;

import org.kszidocs.entity.DocumentsGroup;
import org.kszidocs.service.DocumentService;
import org.kszidocs.service.DocumentsGroupService;
import org.kszidocs.service.SearchService;
import org.kszidocs.web.dto.DocumentDTO;
import org.kszidocs.web.dto.DocumentsGroupDTO;
import org.kszidocs.web.dto.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class RestController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentsGroupService documentsGroupService;

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/documents/add", method = RequestMethod.POST)
    public String addDocumentToGroup(@ModelAttribute("document") DocumentDTO dto,
                                     @RequestParam(value = "file", required = false) MultipartFile file,
                                     RedirectAttributes redirectAttributes) {
        dto.setFile(file);
        documentService.saveDocument(dto);
        return "redirect:/documents/" + dto.getGroup().getUuid();
    }

    @RequestMapping(value = "/documents/delete/{uuid}", method = RequestMethod.POST)
    public String deleteDocument(@PathVariable("uuid") UUID uuid) {
        documentService.deleteDocument(uuid);
        return "redirect:/documents";
    }

    @RequestMapping(value = "/groups/add", method = RequestMethod.POST)
    public String addGroup(@ModelAttribute("group") DocumentsGroupDTO dto) {
        documentsGroupService.saveGroup(dto);
        return "redirect:/documents";
    }

    @RequestMapping(value = "/groups/delete/{uuid}", method = RequestMethod.POST)
    public String deleteGroup(@PathVariable("uuid") UUID uuid) {
        documentService.deleteAllDocumentsByGroupUuid(uuid);
        documentsGroupService.deleteGroup(uuid);
        return "redirect:/documents";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@ModelAttribute("searchDto") SearchDTO dto,
                         RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("searchDto",dto);
        redirectAttributes.addFlashAttribute("documents", searchService.search(dto));
        return "redirect:/search";
    }
}
