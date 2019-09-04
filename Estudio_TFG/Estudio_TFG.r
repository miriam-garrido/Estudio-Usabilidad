vector_acc <- scan( file="Comparativa/UX - Accesibilidad/Acc_vector.txt", sep = ",")
vector_uxa <- scan( file="Comparativa/UX - Accesibilidad/UX_vector.txt", sep = ",")
vector_sos <- scan( file="Comparativa/UX - Sostenibilidad/Sost_vector.txt", sep = ",")
vector_uxs <- scan( file="Comparativa/UX - Sostenibilidad/UX_vector.txt", sep = ",")

rel_usab_sost <- lm(vector_sos ~ vector_uxs)
rel_usab_acc  <- lm(vector_acc ~ vector_uxa)

bounds <- c(0,10)



# Calculamos la media y la desviación típica de los datos relacionados con la usabilidad y la sostenibilidad
print(mean(vector_uxs))
print(mean(vector_sos))
print(sd(vector_uxs))
print(sd(vector_sos))

# Mostramos la relación entre Usabilidad y sostenibilidad
print(rel_usab_sost)

# Mostramos la gráfica asociada a dicha relación
plot(y=vector_sos, x=vector_uxs, abline(rel_usab_sost), ylab="Sostenibilidad",
     xlab="Usabilidad", col="blue", main="Relación entre usabilidad y sostenibilidad",
     xlim=bounds, ylim=bounds)

# Comprobamos si los datos siguen una distribuación normal
shapiro.test(vector_sos)
shapiro.test(vector_uxs)

# Coeficiente de Pearson
print(cor.test(vector_sos, vector_uxs, method = "pearson"))



# Calculamos la media y la desviación típica de los datos relacionados con la usabilidad y la accesibilidad
print(mean(vector_uxa))
print(mean(vector_acc))
print(sd(vector_uxa))
print(sd(vector_acc))

# Mostramos la relación entre Usabilidad y accesibilidad
print(rel_usab_acc)

# Mostramos la gráfica asociada a dicha relación
plot(y=vector_acc, x=vector_uxa, abline(rel_usab_acc), ylab="Accesibilidad",
     xlab="Usabilidad", col="blue", main="Relación entre usabilidad y accesibilidad",
     xlim=bounds, ylim=bounds)

# Comprobamos si los datos siguen una distribuación normal
shapiro.test(vector_acc)
shapiro.test(vector_uxa)

# Coeficiente de Kendall
print(cor.test(vector_acc, vector_uxa, method = "kendall"))

# Coeficiente de Spearman
print(cor.test(vector_acc, vector_uxa, method = "spearman"))


