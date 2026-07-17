library(dplyr)
data <- read.csv("K Means Data.csv")
set.seed(6)
wcsss <- vector()
for (i in 1:10) wcsss[i] <- sum(kmeans(data, i) $withinss)
plot(1:10, wcsss, type = "b",
     main = paste("cluster of pelanggan"),
     xlab = "number",
     ylab = wcsss)
