package de.vogella.android.list.asyncdownload;
public class ImageDownloader {

    public void download(String url, ImageView imageView) {
            BitmapDownloaderTask task = new BitmapDownloaderTask(imageView);
            task.execute(url);
        }
    }

    /* class BitmapDownloaderTask, see below */
}