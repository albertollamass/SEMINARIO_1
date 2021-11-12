# ****************************************
#	Alejandro Herrera Dadgar
# 	Rafael Calvo Córdoba
# 	Jose María Ramírez González
# 	Alberto Llamas González                                        
# 
#	SEMINARIO 1 DDSI                  
#	Curso: 3ºC/C2 - Españita                                              
# 	Fichero: Makefile			                                 
# ****************************************

all: informacion compilar main

informacion: 
	@echo "****************************************"
	@echo "SEMINARIO 1 DDSI" 
	@echo 
	@echo Alejandro Herrera Dadgar 
	@echo Rafael Calvo Córdoba 
	@echo Jose María Ramírez González 
	@echo Alberto Llamas González                                       
               
	@echo Curso: 3ºC/C2 Españita                                            		                                 
	@echo "****************************************"	
	@echo
compilar:
	@echo compilando.....
	javac ./*.java
	@echo
main:
	@echo ejecutando aplicación
	java -cp ojdbc8.jar:. main	
	@echo
 clean:
	-rm ./*.class