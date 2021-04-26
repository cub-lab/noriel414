package com.rsystems.noriel.userrecomandations;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.specialized.BlockBlobClient;
import com.azure.storage.common.StorageSharedKeyCredential;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import com.rsystems.noriel.utils.*;


public class AzureConnServices {

	 /**
     * Entry point into the basic examples for Storage blobs.
     *
     * @param args Unused. Arguments to the program.
     * @throws IOException If an I/O error occurs
     * @throws RuntimeException If the downloaded data doesn't match the uploaded data
     */
	
	
	public File getFileFromContainer(String fileName, String containerName) {
		
		File fileAZ = null;
		
		return fileAZ;
		
	}
	
	public boolean saveFile(String strContent, String fileName, String containerName) {
		
		boolean isSaved = false;
		
	       /*
         * Use your Storage account's name and key to create a credential object; this is used to access your account.
         */
        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(Constants.AZURE_ACCOUNT_NAME, Constants.AZURE_ACCOUNT_KEY);

        /*
         * From the Azure portal, get your Storage account blob service URL endpoint.
         * The URL typically looks like this:
         */
        String endpoint = String.format(Locale.ROOT, "https://%s.blob.core.windows.net", Constants.AZURE_ACCOUNT_NAME);

        /*
         * Create a BlobServiceClient object that wraps the service endpoint, credential and a request pipeline.
         */
        BlobServiceClient storageClient = new BlobServiceClientBuilder().endpoint(endpoint).credential(credential).buildClient();

        /*
         * This example shows several common operations just to get you started.
         */
        
        BlobContainerClient blobContainerClient = storageClient.getBlobContainerClient(Constants.AZURE_DOC_CONTAINER);

        /*
         * Create a client that references a to-be-created blob in your Azure Storage account's container.
         * This returns a BlockBlobClient object that wraps the blob's endpoint, credential and a request pipeline
         * (inherited from containerClient). Note that blob names can be mixed case.
         */
		
        BlockBlobClient blobClient = blobContainerClient.getBlobClient(fileName).getBlockBlobClient();

        String data = strContent;
        InputStream dataStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));


        blobClient.upload(dataStream, data.length());

        try {
        	
			dataStream.close();
			isSaved = true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return isSaved;
		
	}
	
    public void downloadLocalFile(String fileNameFrom, String fileNameTo) throws IOException {

        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(Constants.AZURE_ACCOUNT_NAME, Constants.AZURE_ACCOUNT_KEY);
        String endpoint = String.format(Locale.ROOT, "https://%s.blob.core.windows.net", Constants.AZURE_ACCOUNT_NAME);

        BlobServiceClient storageClient = new BlobServiceClientBuilder().endpoint(endpoint).credential(credential).buildClient();
        
        BlobContainerClient blobContainerClient = storageClient.getBlobContainerClient(Constants.AZURE_DOC_CONTAINER);
        BlockBlobClient blobClient = blobContainerClient.getBlobClient(fileNameFrom).getBlockBlobClient();

        blobClient.downloadToFile(fileNameTo);
        
        System.out.println("post citire");
        

    }
    
    public String downloadAZFile(String fileName) throws IOException {


        /*
         * Use your Storage account's name and key to create a credential object; this is used to access your account.
         */
        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(Constants.AZURE_ACCOUNT_NAME, Constants.AZURE_ACCOUNT_KEY);

        /*
         * From the Azure portal, get your Storage account blob service URL endpoint.
         * The URL typically looks like this:
         */
        String endpoint = String.format(Locale.ROOT, "https://%s.blob.core.windows.net", Constants.AZURE_ACCOUNT_NAME);

        /*
         * Create a BlobServiceClient object that wraps the service endpoint, credential and a request pipeline.
         */
        BlobServiceClient storageClient = new BlobServiceClientBuilder().endpoint(endpoint).credential(credential).buildClient();

        /*
         * This example shows several common operations just to get you started.
         */
        
        BlobContainerClient blobContainerClient = storageClient.getBlobContainerClient(Constants.AZURE_DOC_CONTAINER);

        /*
         * Create a client that references a to-be-created blob in your Azure Storage account's container.
         * This returns a BlockBlobClient object that wraps the blob's endpoint, credential and a request pipeline
         * (inherited from containerClient). Note that blob names can be mixed case.
         */
        BlockBlobClient blobClient = blobContainerClient.getBlobClient(fileName).getBlockBlobClient();
        
        /*
         * Download the blob's content to output stream.
         */
        
        String dataStr = new String();
        
        int dataSize = (int) blobClient.getProperties().getBlobSize();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(dataSize);
        blobClient.download(outputStream);
        
        System.out.println("pre citire");
        
        //blobClient.downloadToFile("/Users/marius.stancalie/Downloads/aaa2.csv");
        //:/home/site/wwwroot
        //blobClient.downloadToFile("/site/wwwroot/aaa2.csv");
        
        System.out.println("post citire");
        
        dataStr = outputStream.toString();      
        outputStream.close();
        
        return dataStr;
    }
    
}
