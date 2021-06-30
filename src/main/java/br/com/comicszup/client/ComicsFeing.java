package br.com.comicszup.client;

import br.com.comicszup.response.ComicsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="marvel", url = "https://gateway.marvel.com:443/v1/public")
public interface ComicsFeing {
    @GetMapping("/comics")
    ComicsResponse getByComicId(@RequestParam("ts") Long ts,
                                @RequestParam("apikey") String apikey,
                                @RequestParam("hash") String hash,
                                @RequestParam("id") String comicId);
}
