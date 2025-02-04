import java.util.Scanner;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner object to handle user input
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter the image file path or URL
        System.out.print("Enter the image file path or URL: ");
        String imagePath = scanner.nextLine();
        
        if (isValidFile(imagePath)) {
            // Call the image recognition
            String detectedDisease = callImageRecognitionSystem(imagePath);
            
            // Generate a report
            String report = generateReport(detectedDisease);
            
            // Print the generated report
            System.out.println(report);
        } else if (isValidURL(imagePath)) {
            BufferedImage image = loadImageFromURL(imagePath);
            if (image != null) {
                String detectedDisease = callImageRecognitionSystem(imagePath);
                String report = generateReport(detectedDisease);
                System.out.println(report);
            } else {
                System.out.println("Error loading image from URL.");
            }
        } else {
            System.out.println("Invalid file path or URL. Please try again.");
        }
        
        // Close the scanner
        scanner.close();
    }
    
    public static boolean isValidFile(String imagePath) {
        File file = new File(imagePath);
        return file.exists() && file.isFile();
    }
    
    public static boolean isValidURL(String urlString) {
        try {
            new URL(urlString).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static BufferedImage loadImageFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            return ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("Error loading image from URL: " + e.getMessage());
            return null;
        }
    }
    
    public static String callImageRecognitionSystem(String imagePath) {
        String disease = "Leaf Blight"; // Simulated result
        return disease;
    }
    
    public static String generateReport(String detectedDisease) {
        StringBuilder report = new StringBuilder();
        
        // Report header
        report.append("\n================ Crop Disease Report ================\n");
        report.append("Detected Disease: ").append(detectedDisease).append("\n");
        report.append("----------------------------------------------------\n");
        
        // Disease-specific recommendations
        if (detectedDisease.equals("Leaf Blight")) {
            report.append("Treatment: Use fungicides containing copper hydroxide.\n");
            report.append("Prevention: Avoid overhead watering and maintain proper plant spacing.\n");
        } else if (detectedDisease.equals("Powdery Mildew")) {
            report.append("Treatment: Apply sulfur-based fungicides.\n");
            report.append("Prevention: Ensure good air circulation and remove infected leaves.\n");
        } else {
            report.append("No specific information available for this disease. Consult an expert.\n");
        }
        
        report.append("====================================================\n");
        
        return report.toString();
    }
}
