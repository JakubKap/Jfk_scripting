// Podaj punkty zdolności kredytowej dla klientów

for each(var e in entities) {
	var score = 0;

	if(e.employed) {
		score += 100 - e.age;
		if(e.married) {
			score += 100;
		}
		score += e.monthSalary / 10 - 100;
		score -= e.numOfChildren * 10;
	}

	print(e.name + " " + e.surname + ": " + score);
}

// Uaktualnij wiek każdego z klientów

for each(var e in entities) {
    e.age++
}

