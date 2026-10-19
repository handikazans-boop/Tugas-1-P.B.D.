import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    static NumberFormat nf =
            NumberFormat.getInstance(new Locale("id", "ID"));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Menu[] daftarMenu = new Menu[8];

        daftarMenu[0] = new Menu("Nasi Katsu", 25000, "Makanan");
        daftarMenu[1] = new Menu("Nasi Goreng", 20000, "Makanan");
        daftarMenu[2] = new Menu("Ayam Bakar", 30000, "Makanan");
        daftarMenu[3] = new Menu("Rawon", 40000, "Makanan");
        daftarMenu[4] = new Menu("Lychee Tea", 12000, "Minuman");
        daftarMenu[5] = new Menu("Americano", 18000, "Minuman");
        daftarMenu[6] = new Menu("Matcha Latte", 18000, "Minuman");
        daftarMenu[7] = new Menu("Aren Latte", 22000, "Minuman");

        tampilkanMenu(daftarMenu);

        System.out.println(
                "\nPilih maksimal 4 item, masukkan 0 jika tidak pesan."
        );

        Menu[] pesanan = new Menu[4];
        int[] jumlah = new int[4];

        System.out.print("\nPilih menu nomor 1 (0-8): ");
        int pilih1 = scanner.nextInt();
        pesanan[0] = ambilMenu(pilih1, daftarMenu);
        if (pilih1 != 0) {
            System.out.print("Jumlah: ");
            jumlah[0] = scanner.nextInt();
        }

        System.out.print("\nPilih menu nomor 2 (0-8): ");
        int pilih2 = scanner.nextInt();
        pesanan[1] = ambilMenu(pilih2, daftarMenu);
        if (pilih2 != 0) {
            System.out.print("Jumlah: ");
            jumlah[1] = scanner.nextInt();
        }

        System.out.print("\nPilih menu nomor 3 (0-8): ");
        int pilih3 = scanner.nextInt();
        pesanan[2] = ambilMenu(pilih3, daftarMenu);
        if (pilih3 != 0) {
            System.out.print("Jumlah: ");
            jumlah[2] = scanner.nextInt();
        }

        System.out.print("\nPilih menu nomor 4 (0-8): ");
        int pilih4 = scanner.nextInt();
        pesanan[3] = ambilMenu(pilih4, daftarMenu);
        if (pilih4 != 0) {
            System.out.print("Jumlah: ");
            jumlah[3] = scanner.nextInt();
        }

        int subtotal = hitungSubtotal(pesanan, jumlah);

        int diskonBOGO = 0;
        if (subtotal > 50000) {
            diskonBOGO = hitungDiskonBOGO(pesanan, jumlah);
        }

        int setelahBOGO = subtotal - diskonBOGO;

        int diskonPersen = 0;
        if (subtotal > 100000) {
            diskonPersen = setelahBOGO * 10 / 100;
        }

        int setelahDiskon = setelahBOGO - diskonPersen;
        int pajak = setelahDiskon * 10 / 100;
        int biayaPelayanan = 20000;
        int totalAkhir = setelahDiskon + pajak + biayaPelayanan;

        cetakStruk(
                pesanan, jumlah, subtotal, diskonBOGO,
                diskonPersen, setelahDiskon, pajak,
                biayaPelayanan, totalAkhir
        );

        scanner.close();
    }

    static Menu ambilMenu(int pilihan, Menu[] daftarMenu) {
        if (pilihan >= 1 && pilihan <= 8) {
            return daftarMenu[pilihan - 1];
        }
        return daftarMenu[0];
    }

    static void tampilkanMenu(Menu[] daftarMenu) {
        System.out.println("\n___ MENU MAKANAN ___");

        System.out.println("1. " + daftarMenu[0].getNama()
                + " - Rp " + nf.format(daftarMenu[0].getHarga()));
        System.out.println("2. " + daftarMenu[1].getNama()
                + " - Rp " + nf.format(daftarMenu[1].getHarga()));
        System.out.println("3. " + daftarMenu[2].getNama()
                + " - Rp " + nf.format(daftarMenu[2].getHarga()));
        System.out.println("4. " + daftarMenu[3].getNama()
                + " - Rp " + nf.format(daftarMenu[3].getHarga()));

        System.out.println("\n___ MENU MINUMAN ___");

        System.out.println("5. " + daftarMenu[4].getNama()
                + " - Rp " + nf.format(daftarMenu[4].getHarga()));
        System.out.println("6. " + daftarMenu[5].getNama()
                + " - Rp " + nf.format(daftarMenu[5].getHarga()));
        System.out.println("7. " + daftarMenu[6].getNama()
                + " - Rp " + nf.format(daftarMenu[6].getHarga()));
        System.out.println("8. " + daftarMenu[7].getNama()
                + " - Rp " + nf.format(daftarMenu[7].getHarga()));
    }

    static int hitungSubtotal(Menu[] pesanan, int[] jumlah) {
        int subtotal = 0;

        if (jumlah[0] > 0) {
            subtotal += pesanan[0].getHarga() * jumlah[0];
        }
        if (jumlah[1] > 0) {
            subtotal += pesanan[1].getHarga() * jumlah[1];
        }
        if (jumlah[2] > 0) {
            subtotal += pesanan[2].getHarga() * jumlah[2];
        }
        if (jumlah[3] > 0) {
            subtotal += pesanan[3].getHarga() * jumlah[3];
        }

        return subtotal;
    }

    static int hitungJumlahMenu(
            Menu target, Menu[] pesanan, int[] jumlah) {

        int totalJumlah = 0;

        if (jumlah[0] > 0
                && pesanan[0].getNama().equals(target.getNama())) {
            totalJumlah += jumlah[0];
        }
        if (jumlah[1] > 0
                && pesanan[1].getNama().equals(target.getNama())) {
            totalJumlah += jumlah[1];
        }
        if (jumlah[2] > 0
                && pesanan[2].getNama().equals(target.getNama())) {
            totalJumlah += jumlah[2];
        }
        if (jumlah[3] > 0
                && pesanan[3].getNama().equals(target.getNama())) {
            totalJumlah += jumlah[3];
        }

        return totalJumlah;
    }

    static boolean sudahDipilihSebelumnya(
            int posisi, Menu[] pesanan, int[] jumlah) {

        if (posisi >= 1 && jumlah[0] > 0
                && pesanan[posisi].getNama()
                .equals(pesanan[0].getNama())) {
            return true;
        }

        if (posisi >= 2 && jumlah[1] > 0
                && pesanan[posisi].getNama()
                .equals(pesanan[1].getNama())) {
            return true;
        }

        if (posisi >= 3 && jumlah[2] > 0
                && pesanan[posisi].getNama()
                .equals(pesanan[2].getNama())) {
            return true;
        }

        return false;
    }

    static int hitungDiskonBOGO(Menu[] pesanan, int[] jumlah) {
        int diskon = 0;

        if (jumlah[0] > 0
                && pesanan[0].getKategori().equals("Minuman")) {
            int totalJumlah =
                    hitungJumlahMenu(pesanan[0], pesanan, jumlah);
            diskon += (totalJumlah / 2) * pesanan[0].getHarga();
        }

        if (jumlah[1] > 0
                && pesanan[1].getKategori().equals("Minuman")
                && !sudahDipilihSebelumnya(1, pesanan, jumlah)) {
            int totalJumlah =
                    hitungJumlahMenu(pesanan[1], pesanan, jumlah);
            diskon += (totalJumlah / 2) * pesanan[1].getHarga();
        }

        if (jumlah[2] > 0
                && pesanan[2].getKategori().equals("Minuman")
                && !sudahDipilihSebelumnya(2, pesanan, jumlah)) {
            int totalJumlah =
                    hitungJumlahMenu(pesanan[2], pesanan, jumlah);
            diskon += (totalJumlah / 2) * pesanan[2].getHarga();
        }

        if (jumlah[3] > 0
                && pesanan[3].getKategori().equals("Minuman")
                && !sudahDipilihSebelumnya(3, pesanan, jumlah)) {
            int totalJumlah =
                    hitungJumlahMenu(pesanan[3], pesanan, jumlah);
            diskon += (totalJumlah / 2) * pesanan[3].getHarga();
        }

        return diskon;
    }

    static void cetakStruk(
            Menu[] pesanan, int[] jumlah, int subtotal,
            int diskonBOGO, int diskonPersen,
            int setelahDiskon, int pajak,
            int biayaPelayanan, int totalAkhir) {

        System.out.println("\n============== STRUK ==============");

        if (jumlah[0] > 0) {
            cetakItem(1, pesanan[0], jumlah[0]);
        }
        if (jumlah[1] > 0) {
            cetakItem(2, pesanan[1], jumlah[1]);
        }
        if (jumlah[2] > 0) {
            cetakItem(3, pesanan[2], jumlah[2]);
        }
        if (jumlah[3] > 0) {
            cetakItem(4, pesanan[3], jumlah[3]);
        }

        System.out.println("-----------------------------------");
        System.out.println("Subtotal             : Rp " + nf.format(subtotal));
        System.out.println("Diskon BOGO          : Rp " + nf.format(diskonBOGO));
        System.out.println("Diskon 10%           : Rp " + nf.format(diskonPersen));
        System.out.println("Total setelah diskon : Rp " + nf.format(setelahDiskon));
        System.out.println("Pajak 10%            : Rp " + nf.format(pajak));
        System.out.println("Biaya pelayanan      : Rp " + nf.format(biayaPelayanan));
        System.out.println("-----------------------------------");
        System.out.println("TOTAL BAYAR          : Rp " + nf.format(totalAkhir));
    }

    static void cetakItem(int nomor, Menu menu, int jumlah) {
        int totalItem = menu.getHarga() * jumlah;

        System.out.println(
                nomor + ". " + menu.getNama()
                + " | " + jumlah + " x Rp " + nf.format(menu.getHarga())
                + " = Rp " + nf.format(totalItem)
        );
    }
}


