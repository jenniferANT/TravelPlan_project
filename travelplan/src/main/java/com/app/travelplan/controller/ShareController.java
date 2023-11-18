package com.app.travelplan.controller;

import com.app.travelplan.model.form.ShareForm;
import com.app.travelplan.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShareController {
    private final ShareService shareService;

    @PostMapping("/api/v1/share")
    public ResponseEntity share(ShareForm shareForm) {
        return new ResponseEntity<>(shareService.share(shareForm), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/share/")
    public ResponseEntity delReceiver(@RequestParam("shareId") long shareId, @RequestParam String receiverId) {
        return new ResponseEntity<>(shareService.delReceiver(shareId, receiverId), HttpStatus.OK);
    }

    //del share for receiver
    @DeleteMapping("/api/v1/share/{id}")
    public ResponseEntity delShare(@PathVariable("id") long shareId) {
        return new ResponseEntity<>(shareService.delShare(shareId), HttpStatus.OK);
    }

    @GetMapping("/api/v1/share/shared")
    public ResponseEntity getMyShareForOther() {
        return new ResponseEntity<>(shareService.getMyShareForOther(), HttpStatus.OK);
    }

    @GetMapping("/api/v1/share/to-shared")
    public ResponseEntity getOtherShareForMe() {
        return new ResponseEntity<>(shareService.getOtherShareForMe(), HttpStatus.OK);
    }
    //user want delete share
}
