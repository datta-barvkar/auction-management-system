package org.scsmksn.npl.auction.web.controllers;

import org.scsmksn.npl.auction.common.controllers.GenericWebController;
import org.scsmksn.npl.auction.model.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.scsmksn.npl.auction.common.utils.AuctionConstants.FORM_ACTION;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.IMAGE_FILE;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.IMAGE_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.IMAGE_NAME;
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.TEAM_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_RESOURCE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_RESOURCE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.UPDATE_IMAGE_ID_FOR_TEAM;

@Controller
@RequestMapping("/resource-management/team")
public class TeamResourceController extends GenericWebController {

    @GetMapping("/{teamId}/image")
    public String uploadImage(@PathVariable(TEAM_ID) final Long teamId, final Model model) {
        model.addAttribute(FORM_ACTION, "/resource-management/team/" + teamId + "/image");
        return "image-upload";
    }

    @GetMapping("/{teamId}/image/{imageId}")
    public String updateImage(@PathVariable(TEAM_ID) final Long teamId, @PathVariable(IMAGE_ID) final Long imageId
            , final Model model) {
        model.addAttribute(FORM_ACTION, "/resource-management/team/" + teamId + "/image/" + imageId);
        return "image-upload";
    }

    @DeleteMapping("/{teamId}/image/{imageId}")
    public String deleteImage(@PathVariable(TEAM_ID) final Long teamId, @PathVariable(IMAGE_ID) final Long imageId) {
        getTemplate().sendBody(DELETE_RESOURCE, imageId);
        getTemplate().sendHeader(UPDATE_IMAGE_ID_FOR_TEAM, TEAM_ID, teamId);
        return "redirect:/team-management/team/" + teamId;
    }

    @PostMapping("/{teamId}/image")
    public String uploadImage(@PathVariable(TEAM_ID) final Long teamId, @RequestParam(IMAGE_NAME) final String imageName
            , @RequestParam(IMAGE_FILE) final MultipartFile imageFile) throws IOException {
        final Resource updatedImage = geUpdatedImage(imageName, imageFile, new Resource());
        final Resource image = getTemplate().requestWithBody(SAVE_RESOURCE, updatedImage, Resource.class);
        final Map<String, Object> headers = new HashMap<>(2);
        headers.put(IMAGE_ID, image.getId());
        headers.put(TEAM_ID, teamId);
        getTemplate().sendHeaders(UPDATE_IMAGE_ID_FOR_TEAM, headers);
        return "redirect:/team-management/team/" + teamId;
    }

    @PutMapping("/{teamId}/image/{imageId}")
    public String updateImage(@PathVariable(TEAM_ID) final Long teamId, @PathVariable(IMAGE_ID) final Long imageId
            , @RequestParam(IMAGE_NAME) final String imageName
            , @RequestParam(IMAGE_FILE) final MultipartFile imageFile) throws IOException {
        final Resource image = new Resource();
        image.setId(imageId);
        getTemplate().sendBody(SAVE_RESOURCE, geUpdatedImage(imageName, imageFile, image));
        return "redirect:/team-management/team/" + teamId;
    }
}
