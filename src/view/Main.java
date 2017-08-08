package view;

import controller.Analyze;
import controller.Ingest;
import model.Data;
import model.Output;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Arjun on 8/8/17.
 *
 * This class performs all the necessary steps for the model
 *
 */
public class Main {

    // average life span of customer for shutterfly is '10'
    public static int AVG_LIFESPAN_SHUTTERFLY = 10;

    // timestamp format in the data
    public static String timestampPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static void main(String args[]) throws IOException {

        // input file path
        String inputFilePath = "./input/input.txt";

        // output file path
        String outputFilePath = "./output/output.txt";

        // number of output records
        int outputSize = 5;

        // performing the ingest
        Data data = Ingest.readInputFileAndIngest(inputFilePath);

        // analyzing the data
        List<Output> output = Analyze.topXSimpleLTVCustomers(outputSize, data);

        // writing the output to a file, The output file contents are as follows
        // 1) CUSTOMER_ID  2) CUSTOMER_NAME  3) CUSTOMER_LTV
        StringBuilder sb = new StringBuilder();
        for (Output each : output) {
            sb.append(each.getCustomer().getCustomerId() + "\t"
                    + each.getCustomer().getLastName() + "\t"
                    + each.getCustomerLTV() + "\n");
        }

        File file = new File(outputFilePath);
        if (file.exists()) file.delete();
        file.createNewFile();
        PrintWriter pw = new PrintWriter(outputFilePath);
        pw.write(sb.toString());
        pw.close();

    }

}
