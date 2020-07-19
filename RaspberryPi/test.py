import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

cred = credentials.Certificate('/home/pi/cred.json')

firebase_admin.initialize_app(cred,{
	'databaseURL':'https://sistemasbogie.firebaseio.com/'
})

ref = db.reference('demo')
print(ref.get())
print('ok!')

