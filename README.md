# Calculadora_IMC

Atividade 5 — Programação para Dispositivos Mobile (Kotlin + Jetpack Compose).

O usuário informa altura (cm) e peso (kg), e o app calcula o IMC e mostra a categoria.

## Regras
- IMC = peso ÷ (altura em metros)²
- Aceita vírgula ou ponto nos valores; campos vazios ou inválidos não são calculados

| IMC | Categoria |
|---|---|
| abaixo de 18,5 | Abaixo do peso |
| 18,5 a 24,9 | Peso ideal |
| 25 a 29,9 | Levemente acima do peso |
| 30 a 34,9 | Obesidade grau I |
| 35 a 39,9 | Obesidade grau II |
| 40 ou mais | Obesidade grau III |

## Estrutura
- `MainActivity.kt` — tela (`ImcScreen`) com o formulário e o card de resultado
- `ui/theme/CalculoIMC.kt` — regras de negócio (`calcularIMC` e `determinarCategoriaIMC`)
