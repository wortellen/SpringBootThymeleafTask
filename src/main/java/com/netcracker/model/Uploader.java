package com.netcracker.model;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class Uploader extends CommonsMultipartResolver {
    private int maxUploadSize = 200000;
}
