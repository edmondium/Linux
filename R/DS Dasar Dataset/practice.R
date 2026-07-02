df <- read.csv("Social_Network_Ads - na.csv")
is.na(df$Age)
df2 <- subset(df, is.na(df$Age) == FALSE)
df2$EstimatedSalary <- ifelse(df2$EstimatedSalary > 1e5,
                              1e5, df2$EstimatedSalary)
df$EstimatedSalary[is.na(df$EstimatedSalary)] <- 0
df$Age[is.na(df$Age)] <- mean(df$Age, na.rm = TRUE)
min(df$Age, na.rm = TRUE)
max(df$Age, na.rm = TRUE)
