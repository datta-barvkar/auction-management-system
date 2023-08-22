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
import static org.scsmksn.npl.auction.common.utils.AuctionConstants.USER_ID;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.DELETE_RESOURCE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.SAVE_RESOURCE;
import static org.scsmksn.npl.auction.common.utils.AuctionRoutes.UPDATE_IMAGE_ID_FOR_USER;

@Controller
@RequestMapping("/resource-management/user")
public class UserResourceController extends GenericWebController {

    @GetMapping("/{userId}/image")
    public String uploadImage(@PathVariable(name = USER_ID) final Long userId, final Model model) {
        model.addAttribute(FORM_ACTION, "/resource-management/user/" + userId + "/image");
        return "image-upload";
    }

    @GetMapping("/{userId}/image/{imageId}")
    public String updateImage(@PathVariable(name = USER_ID) final Long userId
            , @PathVariable(name = "imageId") final Long imageId, final Model model) {
        model.addAttribute(FORM_ACTION, "/resource-management/user/" + userId + "/image/" + imageId);
        return "image-upload";
    }

    @DeleteMapping("/{userId}/image/{imageId}")
    public String deleteImage(@PathVariable(name = USER_ID) final Long userId
            , @PathVariable(name = IMAGE_ID) final Long imageId) {
        getTemplate().sendBody(DELETE_RESOURCE, imageId);
        getTemplate().sendHeader(UPDATE_IMAGE_ID_FOR_USER, USER_ID, userId);
        return "redirect:/user-management/user/" + userId;
    }

    @PostMapping("/{userId}/image")
    public String uploadImage(@RequestParam(name = USER_ID, required = false) final Long userId
            , @RequestParam(IMAGE_NAME) final String imageName, @RequestParam(IMAGE_FILE) final MultipartFile imageFile)
            throws IOException {
        final Resource image = getTemplate().requestWithBody(SAVE_RESOURCE
                , geUpdatedImage(imageName, imageFile, new Resource()), Resource.class);
        final Map<String, Object> headers = new HashMap<>(2);
        headers.put(IMAGE_ID, image.getId());
        headers.put(USER_ID, userId);
        getTemplate().sendHeaders(UPDATE_IMAGE_ID_FOR_USER, headers);
        return "redirect:/user-management/user/" + userId;
    }

    @PutMapping("/{userId}/image/{imageId}")
    public String updateImage(@PathVariable(name = USER_ID) final Long userId
            , @PathVariable(name = IMAGE_ID) final Long imageId, @RequestParam(IMAGE_NAME) final String imageName
            , @RequestParam(IMAGE_FILE) final MultipartFile imageFile) throws IOException {
        final Resource image = new Resource();
        image.setId(imageId);
        getTemplate().sendBody(SAVE_RESOURCE, geUpdatedImage(imageName, imageFile, image));
        return "redirect:/user-management/user/" + userId;
    }
}
