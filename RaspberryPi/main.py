#!/usr/bin/python

# -*- coding: utf-8 -*-



import sys
from time import sleep
import signal
from gpiozero import LED
from threading import Thread
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db


LED = LED(17)
PAHT_CRED = '/home/pi/iot/cred.json'
URL_DB = 'https://sistemabogie.firebaseio.com/'
REF_BOGIE = 'Bogie'
REF_ENCIENDE = 'Encender'



class IOT():


    def __init__(self):

        cred = credentials.Certificate(PAHT_CRED)

        firebase_admin.initialize_app(cred, {

            'databaseURL': URL_DB

        })


        self.refBogie = db.reference(REF_BOGIE)

       

        #self.estructuraInicialDB() # solo ejecutar la primera vez


        self.refEnciende = self.refBogie.child(REF_ENCIENDE)

      

    def estructuraInicialDB(self):

        self.refBogie.set({

            'Enciende': {

                'Bogie_Encendido':True

            }

        })

   

    def controlGPIO(self, estado):

        if estado:

            LED.on()

            print('BOGIE ON')

        else:

            LED.off()

            print('BOGIE OFF')


    def bogieStart(self):


        E, i = [], 0


        estado_anterior = self.refEnciende.get()

        self.controlGPIO(estado_anterior)


        E.append(estado_anterior)


        while True:

          estado_actual = self.refLuzSala.get()

          E.append(estado_actual)


          if E[i] != E[-1]:

              self.controlGPIO(estado_actual)


          del E[0]

          i = i + i

          sleep(0.4)



  


print ('Arrancamos')
iot = IOT()

 #Hilo de las luces
subproceso_led = Thread(target=iot.bogieStart)
subproceso_led.daemon = True
subproceso_led.start()




signal.pause()


