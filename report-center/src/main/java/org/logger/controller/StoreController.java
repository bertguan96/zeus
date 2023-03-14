package org.logger.controller;

import org.logger.strategies.storage.StorageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TBH anglebanghua@gmail.com
 * Date: [2023/3/14 4:55]
 * Description: Test Controller
 */
@RestController
@RequestMapping("storage")
public class StoreController {
    @Autowired
    private StorageContext<Object> storageContext;

    @PostMapping("/objects")
    public void createObject(@RequestBody Object object) {
        storageContext.create(object);
    }
}
