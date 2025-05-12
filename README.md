# 💸 Gerador de Boletos com Padrão Builder – Java

Projeto desenvolvido para geração de boletos bancários seguindo o padrão de projeto **Builder** em Java, com suporte aos bancos **Banco do Brasil**, **Itaú** e **Bradesco**, incluindo geração de PDF com código de barras e logo do banco.

📽️ Assista à demonstração completa do projeto no YouTube:  
[▶️ Clique aqui para ver o vídeo](https://youtu.be/5F9toxvf7HA)

## 🧱 Estrutura do Projeto

- `model/` – Classes de domínio (Beneficiário, Sacado, Endereço, Título)
- `builder/` – Interface `BoletoBuilder`, classes concretas para cada banco e o `GeradorDeBoleto`
- `util/` – Cálculo de Módulo 10/11, geração da linha digitável e código de barras
- `pdf/` – Geração visual do boleto em PDF (usando PDFBox e ZXing)
- `app/Main.java` – Classe de execução, que gera boletos para os três bancos

## 🧰 Tecnologias

- Java 17
- Maven
- [Apache PDFBox](https://pdfbox.apache.org/) – para geração de PDF
- [ZXing](https://github.com/zxing/zxing) – para geração do código de barras (imagem)
- Padrão de projeto **Builder**

## 📄 Funcionalidades

- Geração do código de barras conforme a Febraban
- Montagem da linha digitável com cálculo dos dígitos verificadores
- Exportação de boletos como PDF com:
    - Logo do banco
    - Linha digitável destacada
    - Código de barras como imagem


