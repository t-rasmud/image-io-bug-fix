/**
 * Author shuqi
 * Rev 
 * Date: Mar 26, 2018 5:48:40 PM
 *
 * Copyright (C) 2018 Seeyon, Inc. All rights reserved.
 *
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 * @company		seeyon.com
 * @date		Mar 26, 2018 5:48:40 PM
 * @since		V5 V7.0
 * @author      shuqi
 */
package com.shuqi.imageio.patch.spi;

import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;

import com.shuqi.imageio.patch.plugins.gif.PatchedGIFImageReader;
import com.sun.imageio.plugins.gif.GIFImageMetadata;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.gif.GIFStreamMetadata;

/**
 * @version 0.5
 */
public class PatchedGIFImageReaderSpi extends ImageReaderSpi {

    private static final String vendorName = "Sun Microsystems, Inc.";

    private static final String version = "1.0";

    private static final String[] names = { "gif", "GIF" };

    private static final String[] suffixes = { "gif" };

    private static final String[] MIMETypes = { "image/gif" };

    private static final String readerClassName =
        "com.shuqi.imageio.patch.plugins.gif.PatchedGIFImageReader";

    private static final String[] writerSpiNames = {
        "com.sun.imageio.plugins.gif.GIFImageWriterSpi"
    };

    public PatchedGIFImageReaderSpi() {
        super(vendorName,
              version,
              names,
              suffixes,
              MIMETypes,
              readerClassName,
              new Class[] { ImageInputStream.class },
              writerSpiNames,
              true,
              "javax_imageio_gif_stream_1.0",
              "com.sun.imageio.plugins.gif.GIFStreamMetadataFormat",
              null, null,
              true,
              "javax_imageio_gif_image_1.0",
              "com.sun.imageio.plugins.gif.GIFImageMetadataFormat",
              null, null
              );
    }

    public String getDescription(Locale locale) {
        return "Standard GIF image reader";
    }

    public boolean canDecodeInput(Object input) throws IOException {
        if (!(input instanceof ImageInputStream)) {
            return false;
        }
        
        ImageInputStream stream = (ImageInputStream)input;
        byte[] b = new byte[6];
        stream.mark();
        stream.readFully(b);
        stream.reset();

        return b[0] == 'G' && b[1] == 'I' && b[2] == 'F' && b[3] == '8' &&
            (b[4] == '7' || b[4] == '9') && b[5] == 'a';
    }

    public ImageReader createReaderInstance(Object extension) {
        return new PatchedGIFImageReader(this);
    }

}
