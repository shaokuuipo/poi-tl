/*
 * Copyright 2014-2020 Sayi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.deepoove.poi.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import com.deepoove.poi.data.style.PictureStyle;
import com.deepoove.poi.util.BufferedImageUtils;
import com.deepoove.poi.util.ByteUtils;

/**
 * Picture structure
 * 
 * @author Sayi
 *
 */
public class PictureRenderData implements RenderData {

    private static final long serialVersionUID = 1L;

    private byte[] image;
    private PictureType pictureType;
    private PictureStyle pictureStyle;
    /**
     * When the picture does not exist, the altMeta displayed
     */
    private String altMeta = "";

    PictureRenderData() {
    }

    /**
     * create picture by Local path
     * 
     * @param width  picture width in pixel
     * @param height picture height in pixel
     * @param path   Local picture path
     */
    public PictureRenderData(int width, int height, String path) {
        this(width, height, new File(path));
    }

    /**
     * create picture by Local file
     * 
     * @param width   picture width in pixel
     * @param height  picture height in pixel
     * @param picture Local file
     */
    public PictureRenderData(int width, int height, File picture) {
        this(width, height, PictureType.suggestFileType(picture.getPath()), ByteUtils.getLocalByteArray(picture));
    }

    /**
     * create picture by stream
     * 
     * @param width       picture width in pixel
     * @param height      picture height in pixel
     * @param pictureType {@link PictureType}
     * @param inputStream picture stream
     */
    public PictureRenderData(int width, int height, PictureType pictureType, InputStream inputStream) {
        this(width, height, pictureType, ByteUtils.toByteArray(inputStream));
    }

    /**
     * create picture by bufferedImage
     * 
     * @param width
     * @param height
     * @param pictureType
     * @param image
     */
    public PictureRenderData(int width, int height, PictureType pictureType, BufferedImage image) {
        this(width, height, pictureType, BufferedImageUtils.getBufferByteArray(image, pictureType.format()));
    }

    /**
     * create picture by byte[]
     * 
     * @param width
     * @param height
     * @param pictureType
     * @param data        byte[] data can be generated by tools {@link ByteUtils}
     */
    public PictureRenderData(int width, int height, PictureType pictureType, byte[] data) {
        this.pictureStyle = new PictureStyle();
        this.pictureStyle.setWidth(width);
        this.pictureStyle.setHeight(height);
        this.pictureType = pictureType;
        this.image = data;
    }

    @Deprecated
    public PictureRenderData(int width, int height, String format, InputStream input) {
        this(width, height, format, ByteUtils.toByteArray(input));
    }

    @Deprecated
    public PictureRenderData(int width, int height, String format, BufferedImage image) {
        this(width, height, format, BufferedImageUtils.getBufferByteArray(image, format));
    }

    /**
     * 
     * @param width
     * @param height
     * @param format .png、.jpg
     * @param data
     */
    @Deprecated
    public PictureRenderData(int width, int height, String format, byte[] data) {
        this.pictureStyle = new PictureStyle();
        this.pictureStyle.setWidth(width);
        this.pictureStyle.setHeight(height);
        this.pictureType = PictureType.suggestFileType(format);
        this.image = data;
    }

    public PictureStyle getPictureStyle() {
        return pictureStyle;
    }

    public void setPictureStyle(PictureStyle pictureStyle) {
        this.pictureStyle = pictureStyle;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getAltMeta() {
        return altMeta;
    }

    public void setAltMeta(String altMeta) {
        this.altMeta = altMeta;
    }

    public PictureType getPictureType() {
        return pictureType;
    }

    public void setPictureType(PictureType pictureType) {
        this.pictureType = pictureType;
    }

}
