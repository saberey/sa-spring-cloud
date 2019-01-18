package com.avalon.azure;

import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author jwwang on 2018/11/15
 */
public class BlobBasic {

    private final static Logger logger = LoggerFactory.getLogger(BlobBasic.class);
    /**
     * upload
     * @param containerName
     * @param path
     */
    public  static Boolean upload(String containerName,String path){
        CloudBlobClient blobClient;
        CloudBlobContainer container1 = null;
        Boolean result =false;
        try {
            blobClient = BlobClientProvider.getBlobClientReference();
            container1 = blobClient.getContainerReference(containerName);
            container1 = createContainer(blobClient,containerName);
            File file  = new File(path);
            CloudBlockBlob blob = container1.getBlockBlobReference(file.getName());
            blob.uploadFromFile(file.getAbsolutePath());
            result= true;
            System.out.println("Successfully uploaded the blob.");
        }catch (Throwable t){
            logger.error("Azure blob upload file:{} failed:{}",path,t.getMessage());
        }
        return result;
    }

    public static Boolean exists(String containerName,String fileName){
        CloudBlobClient blobClient;
        CloudBlobContainer container1 = null;
        Boolean result =false;
        try {
            blobClient = BlobClientProvider.getBlobClientReference();
            container1 = blobClient.getContainerReference(containerName);
            File file  = new File(fileName);
            CloudBlockBlob blob = container1.getBlockBlobReference(file.getName());
            if(blob.exists()){
                result= true;
            }
            //System.out.println("Successfully uploaded the blob.");
        }catch (Throwable t){
            logger.error("Azure blob check if file:{} exists failed:{}",fileName,t.getMessage());
        }
        return result;
    }


    /**
     * download
     * @param containerName
     * @param path
     */
    public  static Boolean downLoad(String containerName,String path,String target){
        return downLoad(containerName,path,null,target);
    }

    public  static Boolean downLoad(String containerName,String path,String src,String target){

        CloudBlobClient blobClient;
        CloudBlobContainer container1 = null;
        Boolean result = false;
        if(src==null) {
            src = target;
        }
        try {
            blobClient = BlobClientProvider.getBlobClientReference();
            container1 = blobClient.getContainerReference(containerName);
            File file  = new File(path+File.separator+target);
            if(!file.exists()){
                file.getParentFile().mkdirs();
            }
            CloudBlockBlob blob = container1.getBlockBlobReference(src);
            if(!blob.exists()){
                logger.error("Azure blob file:{} not exists",src);
                return false;
            }
            blob.downloadToFile(file.getAbsolutePath());
            result = true;
            System.out.println("Successfully download the blob.target:"+path+File.separator+target);
        }catch (Throwable t){
            logger.error("Azure blob download file:{} target:{} failed:{}",src,path+File.separator+target,t.getMessage());
        }
        return result;
    }
    public static Boolean remove(String containerName,String blobName){
        CloudBlobClient blobClient;
        CloudBlobContainer container1 = null;
        Boolean result =false;
        try {
            blobClient = BlobClientProvider.getBlobClientReference();
            container1 = blobClient.getContainerReference(containerName);
            CloudBlockBlob blob = container1.getBlockBlobReference(blobName);
            blob.deleteIfExists();
            result = true;
            System.out.println("Successfully remove the blob.");
        }catch (Throwable t){
            logger.error("Azure blob remove file:{} failed:{}",blobName,t.getMessage());
        }
        return result;
    }

    /**
     * Creates and returns a container for the sample application to use.
     *
     * @param blobClient CloudBlobClient object
     * @param containerName Name of the container to create
     * @return The newly created CloudBlobContainer object
     *
     * @throws StorageException
     * @throws RuntimeException
     * @throws IOException
     * @throws URISyntaxException
     * @throws IllegalArgumentException
     * @throws InvalidKeyException
     * @throws IllegalStateException
     */
    private static CloudBlobContainer createContainer(CloudBlobClient blobClient, String containerName) throws StorageException, RuntimeException, IOException, InvalidKeyException, IllegalArgumentException, URISyntaxException, IllegalStateException {

        // Create a new container
        CloudBlobContainer container = blobClient.getContainerReference(containerName);
        try {
            if (container.createIfNotExists() == false) {
                System.out.println(String.format("Container with name %s already exists",containerName));
            }
        }
        catch (StorageException s) {
            if (s.getCause() instanceof java.net.ConnectException) {
                System.out.println("Caught connection exception from the client. If running with the default configuration please make sure you have started the storage emulator.");
            }
            throw s;
        }

        return container;
    }


    public static void main(String[] args) {
        String containerName = "20181203";
        String path = "E:\\工作\\fileTransfer\\download\\2017032320000020170323J00000052683250000010001.pdf";
        String downLoadPath = "E:\\tmpBlobFile";
        String src = "2018120320000020181203J00000101457990000010001.pdf";
        String target = "test.pdf";
        // BlobBasic.upload(containerName,path);
        BlobBasic.downLoad(containerName,downLoadPath,src,target);
        // BlobBasic.remove(containerName,target);
        //System.out.println(BlobBasic.exists(containerName, target));
    }
}
