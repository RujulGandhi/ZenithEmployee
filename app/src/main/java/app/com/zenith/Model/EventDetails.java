package app.com.zenith.Model;

public class EventDetails {
        private String event_id;
        private String event_colorcode;
        private String event_date;
        private String event_name;
        private String event_start_time;
        private String event_end_time;
        private String event_hourly_rate;

        public String getEvent_date() {
            return event_date;
        }

        public void setEvent_date(String event_date) {
            this.event_date = event_date;
        }

        public String getEvent_id() {
            return event_id;
        }

        public void setEvent_id(String event_id) {
            this.event_id = event_id;
        }

        public String getEvent_colorcode() {
            return event_colorcode;
        }

        public void setEvent_colorcode(String event_colorcode) {
            this.event_colorcode = event_colorcode;
        }

        public String getEvent_name() {
            return event_name;
        }

        public void setEvent_name(String event_name) {
            this.event_name = event_name;
        }

        public String getEvent_start_time() {
            return event_start_time;
        }

        public void setEvent_start_time(String event_start_time) {
            this.event_start_time = event_start_time;
        }

        public String getEvent_end_time() {
            return event_end_time;
        }

        public void setEvent_end_time(String event_end_time) {
            this.event_end_time = event_end_time;
        }

        public String getEvent_hourly_rate() {
            return event_hourly_rate;
        }

        public void setEvent_hourly_rate(String event_hourly_rate) {
            this.event_hourly_rate = event_hourly_rate;
        }
    }
