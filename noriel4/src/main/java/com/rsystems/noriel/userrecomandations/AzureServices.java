
package com.rsystems.noriel.userrecomandations;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.specialized.BlockBlobClient;
import com.azure.storage.common.StorageSharedKeyCredential;

import java.io.IOException;
import java.util.Locale;

/**
 * This example shows how to start using the Azure Storage Blob SDK for Java.
 */
public class AzureServices {

    /**
     * Entry point into the basic examples for Storage blobs.
     *
     * @throws IOException      If an I/O error occurs
     * @throws RuntimeException If the downloaded data doesn't match the uploaded data
     */
    public void azureConnection(){

        /*
         * From the Azure portal, get your Storage account's name and account key.
         */
//        String accountName = SampleHelper.getAccountName();
//        String accountKey = SampleHelper.getAccountKey();
        String accountName = "sadfpoccsvdataweu";
        String accountKey = "3MAwSnyyvjluSvjJKOJJoj+dOhuwNSodAIKR9eeMDndVCx22H2NpgJCLGUVv58WLAc5IF471Es44tJ4o02gglA==";

        /*
         * Use your Storage account's name and key to create a credential object; this is used to access your account.
         */
        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accountKey);

        /*
         * From the Azure portal, get your Storage account blob service URL endpoint.
         * The URL typically looks like this:
         */
        String endpoint = String.format(Locale.ROOT, "https://%s.blob.core.windows.net", accountName);

        /*
         * Create a BlobServiceClient object that wraps the service endpoint, credential and a request pipeline.
         */
        BlobServiceClient storageClient = new BlobServiceClientBuilder().endpoint(endpoint).credential(credential).buildClient();

        /*
         * This example shows several common operations just to get you started.
         */

        /*
         * Create a client that references a to-be-created container in your Azure Storage account. This returns a
         * ContainerClient object that wraps the container's endpoint, credential and a request pipeline (inherited from storageClient).
         * Note that container names require lowercase.
         */
        BlobContainerClient blobContainerClient = storageClient.getBlobContainerClient("recommendation"/* + System.currentTimeMillis()*/);

        /*
         * Create a container in Storage blob account.
         */
       // blobContainerClient.create();

        /*
         * Create a client that references a to-be-created blob in your Azure Storage account's container.
         * This returns a BlockBlobClient object that wraps the blob's endpoint, credential and a request pipeline
         * (inherited from containerClient). Note that blob names can be mixed case.
         */
        BlockBlobClient blobClient = blobContainerClient.getBlobClient("item_based_recommendation.csv").getBlockBlobClient();

  /*      String data = "Hello world!";
        InputStream dataStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));

        *//*
         * Create the blob with string (plain text) content.
         *//*
        blobClient.upload(dataStream, data.length());

        try {
            dataStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*
         * Download the blob's content to output stream.
         */
//        int dataSize = (int) blobClient.getProperties().getBlobSize();
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(dataSize);
//        blobClient.download(outputStream);
//        blobClient.downloadToFile("d:/aaa1.txt");
//        try {
//            outputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        /*
         * List the blob(s) in our container.
         */
        blobContainerClient.listBlobs()
                .forEach(blobItem -> System.out.println("Blob name: " + blobItem.getName() + ", Snapshot: " + blobItem.getSnapshot()));

        /*
         * Delete the blob we created earlier.
         */
//        blobClient.delete();

        /*
         * Delete the container we created earlier.
         */
//        blobContainerClient.delete();



    }
}

