library(dplyr)
data <- read.csv("K Means Data.csv")
set.seed(6)
wcsss <- vector()
for (i in 1:10) wcsss[i] <- sum(kmeans(data, i) $withinss)
plot(1:10, wcsss, type = "b",
     main = paste("cluster of pelanggan"),
     xlab = "number",
     ylab = wcsss)
set.seed(29)
kmeans6 <- kmeans(data, 6, iter.max = 10, nstart = 10)
kmeans6$size
kmeans6$cluster
kmeans6$centers
kmeans4 <- kmeans(data, 4, iter.max = 10, nstart = 10)
kmeans4$size
