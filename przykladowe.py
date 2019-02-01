# Print basic info about clients into stderr
import sys

def emp(entity):
	if entity.employed:
		return "employed"
	return "unemployed"

def mar(entity):
	if entity.married:
		return "married"
	return "single"

for e in entities:
    sys.stderr.write(e.name + " " + e.surname + " - age: "+str(e.age)+", "+emp(e)+" "+mar(e)+"\n")

# Swap names and surnames of clients
for e in entities:
    e.name, e.surname = e.surname, e.name
