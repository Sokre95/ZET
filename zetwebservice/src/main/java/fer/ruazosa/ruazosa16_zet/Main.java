package fer.ruazosa.ruazosa16_zet;

public class Main {
    public static void main(String[] args) {
/*
        Retrofit r = new Retrofit.Builder()
                .baseUrl(ZETService.ENDPOINT)
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, Document> responseBodyConverter
                            (Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new DocumentConverter();
                    }
                })
                .build();

        /*************************
        /**** GET ZET ROUTES *****
        /*************************

        /**** BUS DAILY ROUTES ****
        //System.out.println("Dnevne linije autobusa");
        //System.out.println("----------------------");

        ZETService service = r.create(ZETService.class);
        Call<Document> call = service.getRoutes(ZETService.BUS_LINES_DAY_ID);
        try {
            Document document = call.execute().body();
            //List<String> routes = DocumentParser.parseRoutes(document);
            //for(String s : routes) System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**** BUS NIGHT ROUTES ****
        //System.out.println("Nocne linije autobusa");
        //System.out.println("----------------------");

        call = service.getRoutes(ZETService.BUS_LINES_NIGHT_ID);
        try {
            Document document = call.execute().body();
            //List<String> routes = DocumentParser.parseRoutes(document);
            //for(String s : routes) System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**** TRAM DAILY ROUTES ****
        //System.out.println("Dnevne linije tramvaja");
        //System.out.println("----------------------");

        call = service.getRoutes(ZETService.TRAM_LINES_DAY_ID);
        try {
            Document document = call.execute().body();
            //List<String> routes = DocumentParser.parseRoutes(document);
            //for(String s : routes) System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**** TRAM NIGHT ROUTES ****
        //System.out.println("Nocne linije tramvaja");
        //System.out.println("----------------------");

        call = service.getRoutes(ZETService.TRAM_LINES_NIGHT_ID);
        try {
            Document document = call.execute().body();
            //List<String> routes = DocumentParser.parseRoutes(document);
            //for(String s : routes) System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        call = service.getRouteWithDirection(2, 0);
        try {
            Document document = call.execute().body();
            List<String> schedule = DocumentParser.parseSchedule(document, 0);
            //for(String s : schedule) System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        call = service.getRouteWithDirection(2, 0);
        try {
            Document document = call.execute().body();
            String[] polazak = DocumentParser.parseSchedule(document, 0).get(0).split(" ");
            String url = DocumentParser.getUrlForScheduleTime(document, 0, polazak[0]);
            call = service.getSpecificTimeRoute(url);
            Document document1 = call.execute().body();
            List<String> times = DocumentParser.parseRouteWithStationTimes(document1);
            for(String s : times) System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }
}
