#include "helpers.h"
#include <stdio.h>

// Convert image to grayscale
void grayscale(int height, int width, RGBTRIPLE image[height][width])
{
    // Take average of each pixel value
    for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
            RGBTRIPLE triple = image[row][col];
            const BYTE average = (triple.rgbtBlue + triple.rgbtGreen + triple.rgbtRed) / 3;
            triple.rgbtBlue = average;
            triple.rgbtGreen = average;
            triple.rgbtRed = average;
            image[row][col] = triple;
        }
    }
}

// Convert image to sepia
void sepia(int height, int width, RGBTRIPLE image[height][width])
{
    const BYTE maxRgbValue = 255;
    for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
            RGBTRIPLE triple = image[row][col];
            BYTE originalBlue = triple.rgbtBlue;
            BYTE originalRed = triple.rgbtRed;
            BYTE originalGreen = triple.rgbtGreen;

            int sepiaRedInt = .393 * originalRed + .769 * originalGreen + .189 * originalBlue;
            int sepiaGreenInt = .349 * originalRed + .686 * originalGreen + .168 * originalBlue;
            int sepiaBlueInt = .272 * originalRed + .534 * originalGreen + .131 * originalBlue;

            // Convert it back to a BYTE now
            const BYTE sepiaRed = sepiaRedInt > maxRgbValue ? maxRgbValue : sepiaRedInt;
            const BYTE sepiaGreen = sepiaGreenInt > maxRgbValue ? maxRgbValue : sepiaGreenInt;
            const BYTE sepiaBlue = sepiaBlueInt > maxRgbValue ? maxRgbValue : sepiaBlueInt;

            triple.rgbtBlue = sepiaBlue;
            triple.rgbtGreen = sepiaGreen;
            triple.rgbtRed = sepiaRed;
            image[row][col] = triple;
        }
    }
}

// Reflect image horizontally
void reflect(int height, int width, RGBTRIPLE image[height][width])
{
    const int halfWidth = width / 2;
    int currentOffset = 0;
    int leftWidth = halfWidth - currentOffset;
    int rightWidth = halfWidth + currentOffset;
    while (leftWidth >= 0 && rightWidth < width) {
        int row;
        for (row = 0; row < height; row++) {
            // Swap!
            const RGBTRIPLE leftSide = image[row][leftWidth];
            image[row][leftWidth] = image[row][rightWidth];
            image[row][rightWidth] = leftSide;
        }

        currentOffset += 1;
        leftWidth = halfWidth - currentOffset;
        rightWidth = halfWidth + currentOffset;
    }
}

// Blur image
void blur(int height, int width, RGBTRIPLE image[height][width])
{
    const int dirLen = 3;
    const int DIR[] = {-1, 0, 1};
    for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
            int totalRed = 0;
            int totalGreen = 0;
            int totalBlue = 0;
            int totalPixelsCounted = 0;
            // Calculate the average of all surrounding rows
            for (int rowOffset = 0; rowOffset < dirLen; rowOffset++) {
                for (int colOffset = 0; colOffset < dirLen; colOffset++) {
                    const int newRow = row + DIR[rowOffset];
                    const int newCol = col + DIR[colOffset];
                    if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) {
                        const RGBTRIPLE curTriple = image[newRow][newCol];
                        totalRed += curTriple.rgbtRed;
                        totalBlue += curTriple.rgbtBlue;
                        totalGreen += curTriple.rgbtGreen;
                        totalPixelsCounted += 1;
                    }
                }
            }
            RGBTRIPLE triple = image[row][col];
            triple.rgbtRed = totalRed / totalPixelsCounted;
            triple.rgbtBlue = totalBlue / totalPixelsCounted;
            triple.rgbtGreen = totalGreen / totalPixelsCounted;
            image[row][col] = triple;
        }
    }
}
