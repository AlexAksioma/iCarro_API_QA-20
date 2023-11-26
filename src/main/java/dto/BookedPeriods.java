package dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class BookedPeriods {
    private String email;    //": "string",
    private String startDate; //: "2023-11-19",
    private String endDate;   //: "2023-11-19"
}
