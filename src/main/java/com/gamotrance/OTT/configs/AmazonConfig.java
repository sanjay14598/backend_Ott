package com.gamotrance.OTT.configs;
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AmazonConfig {
//    @Bean
//    public AmazonS3 s3() {
//        AWSCredentials awsCredentials =
//                new BasicAWSCredentials("AKIATKD5VBQ6UMH2T55V", "3ivuS11rUzK45UqZn7pYeQOcCDL0BNQKW5RHV0tz");
//        return AmazonS3ClientBuilder
//                .standard()
//                .withRegion("ap-south-1")
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .build();
//
//    }
//}