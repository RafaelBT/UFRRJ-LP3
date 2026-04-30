public class Lutador {
	int hp;

	public void ataca(Lutador outro) {
		outro.apanha(3);
	}

	public void apanha(int qtd) {
		IO.println(this + " perde " + qtd + " pontos!");
		this.hp -= qtd;
	}
}

public class Bárbaro extends Lutador {
	int fúria = 0;

	@Override
	public void ataca(Lutador outro) {
		int força = 5;

		if (this.fúria >= 10) {
			IO.println(this + " luta com fúria");
			força *= 2;
		}

		outro.apanha(força);
	}

	@Override
	public void apanha(int qtd) {
		this.fúria += 1;
		super.apanha(qtd);
	}

	@Override
	public String toString() {
		return "Bárbaro";
	}
}

public class Paladino extends Lutador {
	int fervor = 0;

	@Override
	public void apanha(int qtd) {
		int dano = qtd;
		if (this.fervor >= 10) {
			IO.println(this + " luta com fervor");
			dano /= 3;
		}
		this.fervor += 1;
		super.apanha(dano);
	}

	@Override
	public String toString() {
		return "Paladino";
	}
}

public class Luta {
	Lutador l1;
	Lutador l2;

	public void luta() {
		Random r = new Random();
		while (true) {
			var êxito = r.nextInt(10);
			if ( êxito > 0 ) {
				IO.println(this.l1 + " ataca " + this.l2 + "!");
				l1.ataca(l2);
				if ( l2.hp < 0) {
					IO.println(this.l1 + " venceu");
					break;
				}
			} else {
				IO.println(this.l1 + " errou o ataque!");
			}
			êxito = r.nextInt(10);
			if ( êxito > 0 ) {
				l2.ataca(l1);
				IO.println(this.l2 + " ataca " + this.l1 + "!");
				if ( l1.hp < 0) {
					IO.println(this.l2 + " venceu!");
					break;
				}
			} else {
				IO.println(this.l2 + " errou o ataque!");
			}
		}
	}
}

void main() {
	var b = new Bárbaro();
	var p = new Paladino();

	b.hp = 100;
	p.hp = 100;

	var arena = new Luta();

	arena.l1 = b;
	arena.l2 = p;
	arena.luta();
}

			
		
		
