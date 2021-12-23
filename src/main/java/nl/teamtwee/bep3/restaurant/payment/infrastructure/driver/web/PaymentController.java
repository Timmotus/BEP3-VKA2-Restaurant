package nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web;


import nl.teamtwee.bep3.restaurant.payment.core.application.PaymentCommandHandler;
import nl.teamtwee.bep3.restaurant.payment.core.application.PaymentQueryHandler;
import nl.teamtwee.bep3.restaurant.payment.core.application.command.AddPayment;
import nl.teamtwee.bep3.restaurant.payment.core.application.command.EditPayment;
import nl.teamtwee.bep3.restaurant.payment.core.application.query.GetPaymentById;
import nl.teamtwee.bep3.restaurant.payment.core.application.query.GetPaymentByOrderId;
import nl.teamtwee.bep3.restaurant.payment.core.domain.Payment;
import nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.request.ChangePaymentPayedRequest;
import nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.request.PostPaymentRequest;
import nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.response.PaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/Payment")
public class PaymentController {
    private final PaymentCommandHandler commandHandler;
    private final PaymentQueryHandler queryHandler;

    public PaymentController(PaymentCommandHandler commandHandler, PaymentQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public ResponseEntity registerPayment(@Valid @RequestBody PostPaymentRequest request) {
        AddPayment addPayment = new AddPayment(
                request.orderId,
                request.cost);


        return ResponseEntity.status(HttpStatus.CREATED).body(new PaymentResponse(this.commandHandler.handle(addPayment)));
    }

    @PostMapping("{id}")
    public Payment editPayment(@PathVariable UUID id, @Valid @RequestBody ChangePaymentPayedRequest request) {
        return this.commandHandler.handle(new EditPayment(id, request.payed));
    }


    @GetMapping("/payment/{id}")
    public Payment findPaymentById(@PathVariable UUID id) {
        return this.queryHandler.handle(new GetPaymentById(id));
    }

    @GetMapping("/orderIdPayment/{id}")
    public Payment findPaymentByOrderId(@PathVariable UUID id) {
        return this.queryHandler.handle(new GetPaymentByOrderId(id));
    }
}
